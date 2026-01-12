package com.company.warehouse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryTest {
    public static void main(String[] args) {
        Optional<Result> optionalInventory = Inventory.findItem("A120");

        if(optionalInventory.isPresent()) {
            if(optionalInventory.get() instanceof Inventory) {
                System.out.println(((Inventory) optionalInventory.get()).getPalletItemIds());
            } else if(optionalInventory.get() instanceof ItemPlaceholder) {
                System.out.println(((ItemPlaceholder) optionalInventory.get()).getInfo());
            }
        }

        Inventory inventory = new Inventory("Main Inventory",List.of(
                List.of("P10", "P20"),
                List.of("P30", "P10", "P40")
        ));


        System.out.println(inventory.getPalletItemIds().stream().flatMap(List::stream).collect(Collectors.toSet()));
    }
}
