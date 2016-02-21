package org.kt3k.ebean.enhance

import org.gradle.api.*

public class Plugin : org.gradle.api.Plugin<Project> {

  override fun apply(project: Project) {

    val conf = project.getConfigurations().create("enhance")
    val task = project.task("enhanceEbean")

    task.doLast(object: Action<Task> {

      override fun execute(task: Task) {

        project.ant.invokeMethod("taskdef", mapOf(
          "name" to "enhanceEbean",
          "classname" to "com.avaje.ebean.enhance.ant.AntEnhanceTask",
          "name" to conf.asPath
        ))

        project.ant.invokeMethod("enhanceEbean", mapOf(
          "classSource" to project.buildDir.absolutePath + "src/main",
          "packages" to conf,
          "transformArgs" to "debug=5"
        ))

      }

    })

    (project.property("classes") as Task).dependsOn(task)

  }

}
