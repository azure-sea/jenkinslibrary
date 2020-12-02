package org.devops

// git 拉取 不保留项目目录。例如 jenkins.git 拉取后在当前工作目录只要存在jenkins内的内容。
def checkoutSCM(branch,repoURL,credential){
	checkout([$class: 'GitSCM', branches: [[name: branch]], 
			doGenerateSubmoduleConfigurations: false, 
			extensions: [], 
			submoduleCfg: [], 
			userRemoteConfigs: [[credentialsId: credential, 
			url: repoURL ]]])
}