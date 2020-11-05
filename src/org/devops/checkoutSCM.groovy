package org.devops

// git 拉取
def checkoutSCM(branch,repoURL,credential){
	checkout changlog: false,
	                poll: false,
					scm: [$class: 'GitSCM',
						branches: [[name: branch]],
							extensions: [[$class: 'RelativeTargetDirectory',
							relativeTargetDir: repoURL.split('[/\\.]+')[-2].trim()],
									[$class: 'CleanBeforeCheckout']],
							userRemoteConfigs: [[credentialsId: credential, url: repoURL]]]		
}