# mongo.abelha
This is a sample project to reproduce [Mongock Reflections issue](https://github.com/mongock/mongock/issues/472)

### Application info:
  * Maven
  * JDK 11
  * JAR Packaging
  * Spring Boot - 2.2.6.RELEASE
  * Spring Data MongoDB - 2.2.6.RELEASE
  * Mongock - v5.0.33

### Error description:
* Everything works fine when I run the application on IntelliJ IDE.
* When I run the application by the built *.jar, I get the given error and my migrations are ignored by the library.
* The Same thing happens when run it on a docker container. 

```java
2022-01-26 11:26:44.416  INFO 67213 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2022-01-26 11:26:44.462  INFO 67213 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 42ms. Found 1 MongoDB repository interfaces.
2022-01-26 11:26:44.822  INFO 67213 --- [           main] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
2022-01-26 11:26:44.899  INFO 67213 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:2}] to localhost:27017
2022-01-26 11:26:44.903  INFO 67213 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[5, 0, 2]}, minWireVersion=0, maxWireVersion=13, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=2817047}
2022-01-26 11:26:45.133  INFO 67213 --- [           main] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:3}] to localhost:27017
2022-01-26 11:26:45.185  INFO 67213 --- [-lock-daemon-17] io.mongock.driver.core.lock.LockDaemon   : Starting mongock lock daemon...
2022-01-26 11:26:45.232  INFO 67213 --- [           main] i.m.r.core.builder.RunnerBuilderBase     : Mongock runner COMMUNITY version[5.0.33]
2022-01-26 11:26:45.232  INFO 67213 --- [           main] i.m.r.core.builder.RunnerBuilderBase     : Running Mongock with NO metadata
2022-01-26 11:26:45.232  WARN 67213 --- [           main] i.m.r.core.builder.RunnerBuilderBase     : Property transaction-enabled not provided. It will become true as default in next versions. Set explicit value to false in case transaction are not desired.
2022-01-26 11:26:45.233  WARN 67213 --- [           main] i.m.r.core.builder.RunnerBuilderBase     : Property transaction-enabled not provided and is unknown if driver is transactionable. BY DEFAULT MONGOCK WILL RUN IN NO-TRANSACTION MODE.
2022-01-26 11:26:45.261  WARN 67213 --- [           main] org.reflections.Reflections              : could not create Vfs.Dir from url. ignoring the exception and continuing

java.lang.IllegalStateException: zip file closed
        at java.base/java.util.zip.ZipFile.ensureOpen(ZipFile.java:921) ~[na:na]
        at java.base/java.util.zip.ZipFile.stream(ZipFile.java:648) ~[na:na]
        at java.base/java.util.zip.ZipFile$1.stream(ZipFile.java:1153) ~[na:na]
        at java.base/java.util.jar.JarFile.stream(JarFile.java:544) ~[na:na]
        at org.reflections.vfs.ZipDir.lambda$getFiles$2(ZipDir.java:21) ~[reflections-0.10.1.jar!/:na]
        at org.reflections.Reflections.lambda$scan$2(Reflections.java:177) ~[reflections-0.10.1.jar!/:na]
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183) ~[na:na]
        at java.base/java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1621) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484) ~[na:na]
        at java.base/java.util.stream.ForEachOps$ForEachTask.compute(ForEachOps.java:290) ~[na:na]
        at java.base/java.util.concurrent.CountedCompleter.exec(CountedCompleter.java:746) ~[na:na]
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290) ~[na:na]
        at java.base/java.util.concurrent.ForkJoinTask.doInvoke(ForkJoinTask.java:408) ~[na:na]
        at java.base/java.util.concurrent.ForkJoinTask.invoke(ForkJoinTask.java:736) ~[na:na]
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateParallel(ForEachOps.java:159) ~[na:na]
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateParallel(ForEachOps.java:173) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:233) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:661) ~[na:na]
        at org.reflections.Reflections.scan(Reflections.java:173) ~[reflections-0.10.1.jar!/:na]
        at org.reflections.Reflections.<init>(Reflections.java:126) ~[reflections-0.10.1.jar!/:na]
        at org.reflections.Reflections.<init>(Reflections.java:159) ~[reflections-0.10.1.jar!/:na]
        at io.mongock.runner.core.executor.changelog.ChangeLogServiceBase.mergeChangeLogClassesAndPackages(ChangeLogServiceBase.java:152) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.executor.changelog.ChangeLogServiceBase.fetchChangeLogs(ChangeLogServiceBase.java:130) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.getChangeLogs(RunnerBuilderBase.java:191) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildExecutor(RunnerBuilderBase.java:253) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildRunner(RunnerBuilderBase.java:149) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildRunner(RunnerBuilderBase.java:134) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildRunner(RunnerBuilderBase.java:129) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.springboot.base.builder.SpringbootBuilderBase.buildApplicationRunner(SpringbootBuilderBase.java:98) ~[mongock-springboot-base-5.0.33.jar!/:na]
        at io.mongock.runner.springboot.base.config.MongockContextBase.applicationRunner(MongockContextBase.java:27) ~[mongock-springboot-base-5.0.33.jar!/:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:651) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:636) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1338) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1177) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:557) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:882) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:878) ~[spring-context-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550) ~[spring-context-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at br.com.bruni.mongo.abelha.MongoAbelhaApplication.main(MongoAbelhaApplication.java:12) ~[classes!/:0.0.1-SNAPSHOT]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:48) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:87) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:51) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:52) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]

2022-01-26 11:26:45.265  INFO 67213 --- [           main] org.reflections.Reflections              : Reflections took 17 ms to scan 1 urls, producing 0 keys and 0 values
2022-01-26 11:26:45.274  WARN 67213 --- [           main] org.reflections.Reflections              : could not create Vfs.Dir from url. ignoring the exception and continuing

java.lang.IllegalStateException: zip file closed
        at java.base/java.util.zip.ZipFile.ensureOpen(ZipFile.java:921) ~[na:na]
        at java.base/java.util.zip.ZipFile.stream(ZipFile.java:648) ~[na:na]
        at java.base/java.util.zip.ZipFile$1.stream(ZipFile.java:1153) ~[na:na]
        at java.base/java.util.jar.JarFile.stream(JarFile.java:544) ~[na:na]
        at org.reflections.vfs.ZipDir.lambda$getFiles$2(ZipDir.java:21) ~[reflections-0.10.1.jar!/:na]
        at org.reflections.Reflections.lambda$scan$2(Reflections.java:177) ~[reflections-0.10.1.jar!/:na]
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183) ~[na:na]
        at java.base/java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1621) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484) ~[na:na]
        at java.base/java.util.stream.ForEachOps$ForEachTask.compute(ForEachOps.java:290) ~[na:na]
        at java.base/java.util.concurrent.CountedCompleter.exec(CountedCompleter.java:746) ~[na:na]
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290) ~[na:na]
        at java.base/java.util.concurrent.ForkJoinTask.doInvoke(ForkJoinTask.java:408) ~[na:na]
        at java.base/java.util.concurrent.ForkJoinTask.invoke(ForkJoinTask.java:736) ~[na:na]
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateParallel(ForEachOps.java:159) ~[na:na]
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateParallel(ForEachOps.java:173) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:233) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:661) ~[na:na]
        at org.reflections.Reflections.scan(Reflections.java:173) ~[reflections-0.10.1.jar!/:na]
        at org.reflections.Reflections.<init>(Reflections.java:126) ~[reflections-0.10.1.jar!/:na]
        at org.reflections.Reflections.<init>(Reflections.java:159) ~[reflections-0.10.1.jar!/:na]
        at io.mongock.runner.core.executor.changelog.ChangeLogServiceBase.mergeChangeLogClassesAndPackages(ChangeLogServiceBase.java:154) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.executor.changelog.ChangeLogServiceBase.fetchChangeLogs(ChangeLogServiceBase.java:130) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.getChangeLogs(RunnerBuilderBase.java:191) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildExecutor(RunnerBuilderBase.java:253) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildRunner(RunnerBuilderBase.java:149) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildRunner(RunnerBuilderBase.java:134) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.core.builder.RunnerBuilderBase.buildRunner(RunnerBuilderBase.java:129) ~[mongock-runner-core-5.0.33.jar!/:na]
        at io.mongock.runner.springboot.base.builder.SpringbootBuilderBase.buildApplicationRunner(SpringbootBuilderBase.java:98) ~[mongock-springboot-base-5.0.33.jar!/:na]
        at io.mongock.runner.springboot.base.config.MongockContextBase.applicationRunner(MongockContextBase.java:27) ~[mongock-springboot-base-5.0.33.jar!/:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:651) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:636) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1338) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1177) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:557) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:882) ~[spring-beans-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:878) ~[spring-context-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550) ~[spring-context-5.2.5.RELEASE.jar!/:5.2.5.RELEASE]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.2.6.RELEASE.jar!/:2.2.6.RELEASE]
        at br.com.bruni.mongo.abelha.MongoAbelhaApplication.main(MongoAbelhaApplication.java:12) ~[classes!/:0.0.1-SNAPSHOT]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:48) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:87) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:51) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:52) ~[mongo.abelha-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]

2022-01-26 11:26:45.275  INFO 67213 --- [           main] org.reflections.Reflections              : Reflections took 2 ms to scan 1 urls, producing 0 keys and 0 values
2022-01-26 11:26:45.448  INFO 67213 --- [           main] b.c.b.m.abelha.MongoAbelhaApplication    : Started MongoAbelhaApplication in 1.745 seconds (JVM running for 2.104)
2022-01-26 11:26:45.462  INFO 67213 --- [           main] i.m.r.c.e.o.c.MigrationExecutorBase      : Mongock skipping the data migration. All change set items are already executed or there is no change set item.
2022-01-26 11:26:45.463  INFO 67213 --- [           main] io.mongock.driver.core.lock.LockDaemon   : Cancelling mongock lock daemon...
2022-01-26 11:26:45.463  INFO 67213 --- [           main] i.m.r.c.e.o.c.MigrationExecutorBase      : Mongock has finished
2022-01-26 11:26:45.467  INFO 67213 --- [extShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:2, serverValue:3}] to localhost:27017 because the pool has been closed.
```

### How to reproduce:
* Start MongoDB docker container
  ```shell
  docker-compose up -d
  ```
* Build the application jar
  ```shell
  mvn clean install
  ```
* Run the built jar
  ```shell
  java -jar ./target/mongo.abelha-0.0.1-SNAPSHOT.jar
  ```
