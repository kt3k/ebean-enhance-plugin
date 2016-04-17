package org.kt3k.ebean.enhance

/**
 * The plugin extension.
 */
public open class Extension {

  /**
   * The packages to enhance.
   *
   * ex. "com.exmaple.models.**"
   */
  var packages = ""

  /**
   * Ebean enhance agent group id.
   */
  var agentGroupId = "org.avaje.ebeanorm"

  /**
   * Ebean enhance agent artifact id.
   */
  var agentArtifactId = "avaje-ebeanorm-agent"
  /**
   * Ebean enhance agent version.
   */
  var agentVersion = "4.9.1"

  /**
   *
   */
  var antEnhanceTaskClassName = "com.avaje.ebean.enhance.ant.AntEnhanceTask"

  /**
   * Transoformation args for enhance agent.
   */
  var transformArgs = "debug=5"

}
