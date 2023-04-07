/**
  * Author: Manjunath Davanam <manjunathdavanam@gmail.com>
  */
package com.sahaj.services

import com.typesafe.config.{Config, ConfigFactory}

/**
  * AppConfig- Service to get the configurations
  */
object AppConfig {
  lazy val defaultConf: Config = ConfigFactory.load()
  lazy val envConf: Config = ConfigFactory.systemEnvironment()
  lazy val conf: Config = defaultConf.withFallback(envConf)

  /**
    * Method to get the config
    *
    * @param key - object key
    * @return -  object value
    */
  def getConfig(key: String): String = {
    if (conf.hasPath(key))
      conf.getString(key)
    else ""
  }
}