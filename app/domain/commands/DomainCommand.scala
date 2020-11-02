package domain.commands

sealed trait DomainCommand

case class Add() extends DomainCommand
case class Start() extends DomainCommand
case class Complete() extends DomainCommand