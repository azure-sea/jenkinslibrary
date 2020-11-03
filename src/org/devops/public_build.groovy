package org.devops

// 更新公共库并构建
def public_build(git_public_user,srcUrl,branchName){
	checkout([$class: 'GitSCM', branches: [[name: "${branchName}"]], 
				  doGenerateSubmoduleConfigurations: false, 
				  extensions: [], 
				  submoduleCfg: [], 
				  userRemoteConfigs: [[credentialsId: '${git_public_user}', url: "${srcUrl}"]]])
}
