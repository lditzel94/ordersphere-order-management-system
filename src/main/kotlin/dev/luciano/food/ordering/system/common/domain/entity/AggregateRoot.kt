package dev.luciano.food.ordering.system.common.domain.entity

abstract class AggregateRoot<ID>(id: ID) : BaseEntity<ID>(id)