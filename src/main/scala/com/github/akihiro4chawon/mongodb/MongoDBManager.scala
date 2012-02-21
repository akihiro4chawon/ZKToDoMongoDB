package com.github.akihiro4chawon.mongodb

import com.mongodb.DB
import com.mongodb.Mongo
import com.google.code.morphia.Morphia
import scala.reflect.BeanProperty

object MongoDBManager {
  @BeanProperty lazy val mongo = new Mongo
  @BeanProperty lazy val morphia = locally {
    val morph = new Morphia
    morph.mapPackage("com.github.akihiro4chawon.mongodb.model")
    morph
  }
  
  def getDB(dbName: String) = {
    mongo.getDB(dbName)
  }
}
