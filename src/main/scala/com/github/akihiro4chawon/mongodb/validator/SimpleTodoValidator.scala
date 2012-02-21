package com.github.akihiro4chawon.mongodb.validator

import java.util.Date
import org.zkoss.bind.ValidationContext
import org.zkoss.bind.validator.AbstractValidator

class SimpleTodoValidator extends AbstractValidator {
  def validate(ctx: ValidationContext) {
    def onProperty(prop: String)(validate: Any => Either[String, Unit]) {
      validate(ctx.getProperties(prop)(0).getValue).left foreach { msg =>
        addInvalidMessage(ctx, prop, msg)  
      }
    }
    
    onProperty("name") {
      case name: String if name.nonEmpty => Right()
      case _ => Left("You must enter a name")
    }
    onProperty("executionDate") {
      case _: Date => Right()
      case _ => Left("You must specify a date")
    }
    onProperty("priority") {
      case priority: Int if 1 <= priority && priority <= 10 => Right()
      case _ => Left("You must give a priority > 0 && < 10")
    }
  }
}
