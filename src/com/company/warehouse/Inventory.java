package com.company.warehouse;

import java.util.List;
import java.util.Optional;

public final class Inventory implements Result {
    private String name;
    private List<List<String>> palletItemIds; // Nested structure: List of Pallets, where each Pallet is a List of Item IDs

    public Inventory(String name, List<List<String>> palletItemIds) {
        this.name = name;
        this.palletItemIds = palletItemIds;
    }

    public List<List<String>> getPalletItemIds() { return palletItemIds; }

    // Simulates an expensive database lookup
    public static Optional<Result> findItem(String id) {
        if (id.equals("A100")) {
            return Optional.of(new Inventory("Main Inventory", List.of(
                    List.of("P10", "P20"),
                    List.of("P30", "P10", "P40")
            )));
        }
        return Optional.of(new ItemPlaceholder()); // Search fails
    }
}

// File: ItemPlaceholder.java
final class ItemPlaceholder implements Result {

    // This is a highly resource-intensive object to create.
    public ItemPlaceholder() {
        System.out.println("ALERT: Creating expensive placeholder object!");
    }
    public String getInfo() { return "ID-NOT-FOUND: Placeholder Item"; }
}
