
#### Variaveis de ambiente
    sudo nano /etc/environment
    export JAVA_HOME=/opt/app_dev/jdk-21.0.8+9
    export PATH=/opt/app_dev/jdk-21.0.8+9/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin
    export POSTGRESQL_USER=postgres
    export POSTGRESQL_PASSWORD=postgres
    export POSTGRESQL_DATABASE=liberty-erp
    export POSTGRESQL_URL=jdbc:postgresql://localhost:5432/liberty-erp


### Modo de Debug

#### Cria o server configurado 
    
    mvn clean wildfly:package


#### Execução do projeto/servidor

    mvn wildfly:dev

#### Configuração no Ideia
    Run Debug > Remote JVM Debug 
        Options:
                Debuger Mode: Attach to remote JVM 
                Port: 8787

#### Compilar o sass
mais opcões de configuração https://github.com/cleydyr/dart-sass-maven-plugin

    mvn io.github.cleydyr:dart-sass-maven-plugin:1.5.0:compile-sass
