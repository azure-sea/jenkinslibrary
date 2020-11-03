package org.devops

// 更新公共库并构建
def public_build(){
    sh "pwd"
	sh "/usr/bin/pip3 install wheel"
	sh "/usr/bin/python3 setup.py bdist_wheel --universal"
}
