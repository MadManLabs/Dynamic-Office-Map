node {
	def mvn, docker
	def project = "dynamicofficemap"
	def external_apps = ['MongoDB']
	
	stage('Preparation') {
		checkout scm
		
		def mvnHome = tool 'M3'
		mvn = "${mvnHome}/bin/mvn"
		
		def dockerHome = tool 'Docker'
		docker = "${dockerHome}/bin/docker"
	}
	
	stage('Build Server') {
		sh "${mvn} -f DOMServer/server/pom.xml clean package -U"
	}
	
	stage('Build Web Client') {
		sh "${mvn} -f DOMServer/web-client/pom.xml clean package -U"
	}
	
	stage('Docker Image Build') {
		sh "docker-compose build"
	}
	
	stage('Docker Network Disconnect') {
		external_apps.each { app ->
			sh "${docker} network disconnect ${project}_default ${app} || true"
		}
	}
	
	stage('Docker Deploy') {
		sh "docker-compose -p ${project} down"
		sh "docker-compose -p ${project} up -d"
	}
	
	stage('Docker Network Connect') {
		external_apps.each { app ->
			sh "${docker} network connect ${project}_default ${app} || true"
		}
	}
	
}