#!groovy

node {
    def projName = 'hmtf-user-services'

    deleteDir()

    def gradle = "./gradlew"
    def repoUrl = 'git@github.com:douglasffilho/hmtf-user-services.git'
    def branch = '*/master'

    def credentials = 'd0d1c605-a0e9-4b83-be59-a5a1c45da8be'

    try {
        //sendMail('Delivery iniciado ${projName}.', 'Delivery ${projName} TAG:${versionIncremented}, iniciado.')

        stage(name: "Clone", concurrency: 1)
        echo 'Clonando repositorio...'
        checkout([$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: credentials, url: repoUrl]]])
        echo 'Repositorio clonado.'
        sh "ls"

        stage(name: "Clean", concurrency: 1)
        echo 'Limpando o ambiente...'
        sh "${gradle} clean"
        echo 'Ambiente limpo.'

        stage(name: "Build", concurrency: 1)
        echo 'Realizando build do projeto...'
        //sh "${gradle} build -x test --stacktrace"
        sh "${gradle} assemble --stacktrace"
        echo 'Build concluido com exito.'

        //stage(name: "Test", concurrency: 1)
        //try {
        //   sh "${gradle} test --stacktrace "
        //}
        //finally {
        //   step([$class: "JUnitResultArchiver", testResults: "**/build/test-results/test/TEST-*.xml"])
        //}

        //sendMail('Delivery finalizado com Sucesso.', 'Delivery ${projName} TAG:${tagRelease}, entregue em Produção com sucesso.')

    } catch (org.jenkinsci.plugins.workflow.steps.FlowInterruptedException fie) {
        throw fie
    } catch (Exception e) {
        throw e
    }
}

def sendMail(subject, message) {
    sendMsgToEmail('root', subject, message)
}


def sendMsgToEmail(to, subject, message) {
     mail(
            from: "root",
            to: to,
            replyTo: "root",
            subject: subject,
            body: message
    )
}
