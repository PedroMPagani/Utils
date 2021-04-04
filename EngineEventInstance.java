import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class EngineEventInstance implements Listener {


    @EventHandler(ignoreCancelled = true,priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent e){
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (e.getItem() == null) return;
        int id = e.getItem().getTypeId();
        if(!isArmor(id)) return;
        Player p = e.getPlayer();
        String itemname = e.getItem().getType().name().toLowerCase();
        String finalst = itemname.split("_")[1];
        // armor names are all like: gold_helmet,diamond_chestplate,diamond_boots, so if you split the "_" and get the last index, it will return boots, helmet etc
        ItemStack itemStack = e.getItem();
        PlayerInventory pinventory = p.getInventory();
        switch (finalst){
            case "helmet":{
                if (pinventory.getHelmet() == null){
                    ArmorEvent armorEvent = new ArmorEvent(p,itemStack,true);
                    Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                }
                return;
            }
            case "chestplate":{
                if (pinventory.getChestplate() == null){
                    ArmorEvent armorEvent = new ArmorEvent(p,itemStack,true);
                    Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                }
                return;
            }
            case "leggings":{
                if (pinventory.getLeggings() == null){
                    ArmorEvent armorEvent = new ArmorEvent(p,itemStack,true);
                    Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                }
                return;
            }
            case "boots":{
                if (pinventory.getBoots() == null){
                    ArmorEvent armorEvent = new ArmorEvent(p,itemStack,true);
                    Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                }
                return;
            }
        }
    }

    @EventHandler
    public void onArmor(ArmorEvent event){
        System.out.println("ArmorEvent called: " + event.isEquip);
    }

    @EventHandler(ignoreCancelled = true,priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent e){
        if (e.getClickedInventory() == null) return;
        if (e.getSlotType() == InventoryType.SlotType.FUEL) return;
        if (e.getSlotType() == InventoryType.SlotType.RESULT) return;
        if (e.getSlotType() == InventoryType.SlotType.CRAFTING) return;
        if (e.getSlotType() == InventoryType.SlotType.OUTSIDE) return;
        if (e.getCurrentItem() == null) return;
        System.out.println(e.getClick().name() + ": "+e.getRawSlot());
        if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
            Player p = (Player) e.getWhoClicked();
            ItemStack itemStack = e.getCurrentItem();
            PlayerInventory pinventory = p.getInventory();
            if (e.getClick() == ClickType.NUMBER_KEY){
                if (e.getRawSlot() == 5 || e.getRawSlot() == 6 || e.getRawSlot() == 7 || e.getRawSlot() == 8){
                    System.out.println("raw number");
                    if (pinventory.getItem(e.getRawSlot()) != null){
                        System.out.println("raw number 2: item isnt null and not empty == -1");
                        if (isArmor(pinventory.getItem(e.getRawSlot()).getTypeId())){
                            System.out.println("raw number and is armor boolean firstempty: " + (p.getInventory().firstEmpty() == -1));
                            if (p.getInventory().firstEmpty() == -1) return;
                            ArmorEvent armorEvent = new ArmorEvent(p, itemStack,false);
                            Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            return;
                        }
                        if (pinventory.getItem(e.getHotbarButton()) == null){
                            itemStack = pinventory.getItem(e.getRawSlot());
                            String itemname = itemStack.getType().name().toLowerCase();
                            System.out.println(itemname);
                            if (isArmor(itemStack.getTypeId())) {
                                System.out.println("is armor");
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, false);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (pinventory.getItem(e.getHotbarButton()) == null){
                        System.out.println(e.getCurrentItem().getType().name());
                        System.out.println(pinventory.getItem(e.getRawSlot()));
                        String itemname = itemStack.getType().name().toLowerCase();
                        System.out.println(itemname);
                        if (isArmor(itemStack.getTypeId())) {
                            System.out.println("is armor");
                            ArmorEvent armorEvent = new ArmorEvent(p, itemStack, false);
                            Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            return;
                        }
                        return;
                    }
                    if (pinventory.getItem(e.getHotbarButton()) != null){
                        itemStack = pinventory.getItem(e.getHotbarButton());
                        String itemname = itemStack.getType().name().toLowerCase();
                        String finalst = itemname.split("_")[1];
                        if (isArmor(itemStack.getTypeId())) {
                            switch (finalst) {
                                case "helmet": {
                                    if (pinventory.getHelmet() == null) {
                                        ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                        Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                                    }
                                    return;
                                }
                                case "chestplate": {
                                    if (pinventory.getChestplate() == null) {
                                        ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                        Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                                    }
                                    return;
                                }
                                case "leggings": {
                                    if (pinventory.getLeggings() == null) {
                                        ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                        Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                                    }
                                    return;
                                }
                                case "boots": {
                                    if (pinventory.getBoots() == null) {
                                        ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                        Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                                    }
                                    return;
                                }
                            }
                            return;
                        }
                    }
                    return;
                }
            }
            if (e.getCursor() != null) {
                if (isArmor(e.getCursor().getTypeId())) {
                    if (e.getSlotType() == InventoryType.SlotType.ARMOR) {
                        System.out.println(e.isCancelled());
                        if (e.isCancelled()) return;
                        ArmorEvent armorEvent = new ArmorEvent(p, e.getCursor(), (pinventory.getItem(e.getRawSlot()) == null));
                        Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                        return;
                    }
                }
            }
            if (e.getCursor() == null) {
                if (isArmor(e.getCurrentItem().getTypeId())){
                    if (e.getSlotType() == InventoryType.SlotType.ARMOR){
                        System.out.println(e.isCancelled());
                        if (e.isCancelled()) return;
                        ArmorEvent armorEvent = new ArmorEvent(p, e.getCursor(), false);
                        Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                        return;
                    }
                }
            }
            // keystrokes
            if (isArmor(e.getCurrentItem().getTypeId())){
                String itemname = e.getCurrentItem().getType().name().toLowerCase();
                String finalst = itemname.split("_")[1];
                if (e.getSlotType() == InventoryType.SlotType.ARMOR){
                    switch (finalst) {
                        case "helmet": {
                            if (pinventory.getHelmet() != null) {
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, false);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                        case "chestplate": {
                            if (pinventory.getChestplate() != null) {
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, false);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                        case "leggings": {
                            if (pinventory.getLeggings() != null) {
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, false);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                        case "boots": {
                            if (pinventory.getBoots() != null) {
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, false);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                    }
                    return;
                }
                if (e.isShiftClick()) {
                    switch (finalst) {
                        case "helmet": {
                            if (pinventory.getHelmet() == null) {
                                if (p.getInventory().firstEmpty() == -1) return;
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                        case "chestplate": {
                            if (pinventory.getChestplate() == null) {
                                if (p.getInventory().firstEmpty() == -1) return;
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                        case "leggings": {
                            if (pinventory.getLeggings() == null) {
                                if (p.getInventory().firstEmpty() == -1) return;
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                        case "boots": {
                            if (pinventory.getBoots() == null) {
                                if (p.getInventory().firstEmpty() == -1) return;
                                ArmorEvent armorEvent = new ArmorEvent(p, itemStack, true);
                                Bukkit.getServer().getPluginManager().callEvent(armorEvent);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    public static boolean isArmor(int id){
        return id > 297 && id < 318;
    }

    public static class ArmorEvent extends Event {

        private static final HandlerList handlers = new HandlerList();

        private boolean isEquip;
        private ItemStack armorPiece;
        private Player player;

        /**
         *
         * @param p Player that it is equiping the ItemStack
         * @param armor The armor item.
         */
        public ArmorEvent(Player p,ItemStack armor){
            this.player = p;
            this.armorPiece = armor;
            this.isEquip = true;
        }

        /**
         *
         * @param p Player that it is equiping the ItemStack
         * @param armor The armor item.
         * @param isEquipEvent is equip or unequip amor
         */
        public ArmorEvent(Player p,ItemStack armor,boolean isEquipEvent){
            this.player = p;
            this.armorPiece = armor;
            this.isEquip = isEquipEvent;
        }

        public HandlerList getHandlers() {
            return handlers;
        }

        public boolean isEquip() {
            return isEquip;
        }

        public static HandlerList getHandlerList() {
            return handlers;
        }

        public ItemStack getArmorPiece() {
            return armorPiece;
        }

        public Player getPlayer() {
            return player;
        }
    }

}
