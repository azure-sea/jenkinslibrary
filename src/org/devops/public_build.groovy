package org.devops

// 更新公共库并构建
def public_build(git_user_auth,public_branch_or_tag,git_url){
	checkout([$class: 'GitSCM', branches: [[name: "${public_branch_or_tag}"]], 
			doGenerateSubmoduleConfigurations: false, 
			extensions: [], 
			submoduleCfg: [], 
			userRemoteConfigs: [[credentialsId: '${git_user_auth}', 
			url: "${git_url}"]]])
}
