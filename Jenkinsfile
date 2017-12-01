node {
	def mvn, docker
	def project = 'dynamicofficemap'
	def externalContainers = ['MongoDB']
	
	stage('Preparation') {
		checkout scm
		
		def mvnHome = tool 'M3'
		mvn = "${mvnHome}/bin/mvn"
		
		def dockerHome = tool 'Docker'
		docker = "${dockerHome}/bin/docker"
	}
	
	stage('Docker Start Required Containers') {
		externalContainers.each { container ->
			sh "${docker} start ${container} || true"
		}
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
		externalContainers.each { container ->
			sh "${docker} network disconnect ${project}_default ${container} || true"
		}
	}
	
	stage('Docker Deploy') {
		sh "docker-compose -p ${project} down"
		sh "docker-compose -p ${project} up -d"
	}
	
	stage('Docker Network Connect') {
		externalContainers.each { container ->
			sh "${docker} network connect ${project}_default ${container} || true"
		}
	}
	
}