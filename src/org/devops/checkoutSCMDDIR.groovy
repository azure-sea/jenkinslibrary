package org.devops

// git 拉取 保留项目目录。例如 jenkins.git 拉取后在当前工作目录下存在 jenkins 目录 
def checkoutSCMDDIR(branch,gitcheckpath,repoURL,credential){
	checkout changlog: false,
	                poll: false,
					scm: [$class: 'GitSCM',
						branches: [[name: branch]],
							extensions: [[$class: 'RelativeTargetDirectory',
							relativeTargetDir: repoURL.split('[/\\.]+')[-2].trim()],
							[$class: 'CleanBeforeCheckout']
							[$class: 'SparseCheckoutPaths', sparseCheckoutPaths: [[path: 'gitcheckpath']]],
							userRemoteConfigs: [[credentialsId: credential, url: repoURL]]]		
}