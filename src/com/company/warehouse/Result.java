package com.company.warehouse;

public sealed interface Result permits Inventory, ItemPlaceholder {
}
