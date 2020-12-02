package org.devops
 
def getChangeString() {
    def changeString = ""
    def MAX_MSG_LEN = 20
    def changeLogSets = currentBuild.changeSets
for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            truncatedMsg = entry.msg.take(MAX_MSG_LEN)
            commitTime = new Date(entry.timestamp).format("yyyy-MM-dd HH:mm:ss")
            changeString += " - ${truncatedMsg} [${entry.author} ${commitTime}]\n"
        }
    }
if (!changeString) {
        changeString = " - No new changes"
    }
return (changeString)
}
 
 
def HttpReq(Deploy_env,code_branch_or_tag,Status,CatchInfo=' '){
    wrap([$class: 'BuildUser']){
        def DingTalkHook = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxxx"
        def ChangeLog = getChangeString()
        def ReqBody = """{
            "msgtype": "markdown",
            "markdown": {
                "title": "项目构建信息",
                "content": "### 构建信息\n>应用名称: ${env.JOB_NAME}\n第 **${env.BUILD_NUMBER}** 构建\n 构建环境: ${Deploy_env}\n 构建结果: ${Status} ${CatchInfo} \n 当前版本: ${code_branch_or_tag}\n 构建发起: ${env.BUILD_USER}\n 持续时间: ${currentBuild.durationString}\n 构建日志: [点击查看详情](${env.BUILD_URL}console)\n #### 更新记录: \n${ChangeLog}"
            },
            }"""
// println(currentBuild.description)
// println(currentBuild.changeSets)
        httpRequest acceptType: 'APPLICATION_JSON_UTF8', 
                consoleLogResponseBody: false, 
                contentType: 'APPLICATION_JSON_UTF8', 
                httpMode: 'POST', 
                ignoreSslErrors: true, 
                requestBody: ReqBody, 
                responseHandle: 'NONE', 
                url: "${DingTalkHook}",
                quiet: true
    }
}