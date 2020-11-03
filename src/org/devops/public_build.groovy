package org.devops

// 更新公共库并构建
def public_build(gitpublicuser,srcUrl,branchName){
	checkout([$class: 'GitSCM', branches: [[name: "${branchName}"]], 
				  doGenerateSubmoduleConfigurations: false, 
				  extensions: [], 
				  submoduleCfg: [], 
				  userRemoteConfigs: [[credentialsId: '${gitpublicuser}', url: "${srcUrl}"]]])
}
