package net.academy.common.items;

import net.academy.common.anotiation.comentState.ComentState;
import net.academy.common.anotiation.comentState.State;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
@ComentState(commentState = State.FINISHED)
public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;


    /**
     * creates a new ItemStack with this Material
     * @param material to create a new ItemStack
     */
    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    /**
     * creates a new ItemBuilder from an existing ItemStack
     * @param item to create the ItemBuilder over
     */
    public ItemBuilder(ItemStack item) {
        itemStack = item;
        itemMeta = item.getItemMeta();
    }

    /**
     * Set the Display-name for the Item to the ItemMeta.
     * The ItemMeta is not set to the item here
     * @param name the string to set the display name to
     */
    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * set the unbreakable metaData for the ItemStack
     * @param statement the statement to set the unbreakable mode.
     */
    public ItemBuilder setUnbreakable(Boolean statement) {
        itemMeta.setUnbreakable(statement);
        return this;
    }

    /**
     * set the amount of the ItemStack
     * @param amount the amount of the itemStack
     */
    public ItemBuilder setAmount(Integer amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * set the Material of the ItemStack
     * @param material the material what is used
     */
    public ItemBuilder setMaterial(Material material) {
        itemStack.setType(material);
        return this;
    }

    /**
     * set the CustomModelData for the ItemStack
     * @param customModelData the int what is used
     */
    public ItemBuilder setCustomModelData(Integer customModelData) {
        itemMeta.setCustomModelData(customModelData);
        return this;
    }

    /**
     * set the lore from the itemMeta
     * @param lores the Strings, for the lores
     */
    public ItemBuilder setLore(String... lores) {
        itemMeta.setLore(Arrays.asList(lores));
        return this;
    }

    /**
     * set the lore from the itemMeta
     * @param lores the list<String> what is used for the lore
     */
    public ItemBuilder setLore(List<String> lores) {
        itemMeta.setLore(lores);
        return this;
    }

    /**
     * removes a single line form the itemMeta
     * @param line the String what get removed
     */
    public ItemBuilder removeLoreLine(String line) {
        List<String> lore = new ArrayList<>();
        if(itemMeta.hasLore()) lore = itemMeta.getLore();
        if (!lore.contains(line)) return this;
        lore.remove(line);
        itemMeta.setLore(lore);
        return this;
    }

    /**
     * remove multiple strings from the lore
     * @param lines the lines what are used
     */
    public ItemBuilder removeLoreLines(String... lines) {
        List<String> lore = new ArrayList<>();
        if(itemMeta.hasLore()) lore = itemMeta.getLore();

        for (String s : lines) {
            lore.remove(s);
        }
        itemMeta.setLore(lore);
        return this;
    }

    /**
     * remove a single line by the index
     * @param index that is used to remove
     */
    public ItemBuilder removeLoreLine(int index) {
        List<String> lore = new ArrayList<>();
        if(itemMeta.hasLore()) lore = itemMeta.getLore();

        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        itemMeta.setLore(lore);
        return this;
    }

    /**
     * remove multiple lines by the indexes
     * @param indexes that are used to remove
     */
    public ItemBuilder removeLoreLines(int... indexes) {
        List<String> lore = new ArrayList<>();
        if(itemMeta.hasLore()) lore = itemMeta.getLore();

        for (int index : indexes) {
            if (!(index < 0 || index > lore.size()))
                lore.remove(index);
        }

        itemMeta.setLore(lore);
        return this;
    }

    /**
     * add a single lore line to the item lores
     * @param lore the string that will be added
     */
    public ItemBuilder addLoreLine(String lore) {
        List<String> lores = new ArrayList<>();
        if (itemMeta.hasLore()) lores = itemMeta.getLore();
        lores.add(lore);
        itemMeta.setLore(lores);
        return this;
    }

    /**
     * add multiple lores
     * @param lores the lines that are used
     */
    public ItemBuilder addLoreLines(String... lores) {
        List<String> loresList = new ArrayList<>();
        if (itemMeta.hasLore()) loresList = itemMeta.getLore();

        loresList.addAll(Arrays.asList(lores));

        itemMeta.setLore(loresList);
        return this;
    }

    /**
     * Sets the dye color on an item.
     * <b> Notice that this doesn't check for item type, sets the literal data of the dye color as durability.</b>
     * @param color The color to put.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setDyeColor(DyeColor color) {
        itemStack.setDurability(color.getDyeData());
        return this;
    }

    /**
     * Sets the armor color of a leather armor piece.
     * Works only on leather armor pieces.
     * @param color The color to set it to.
     */
    @Nullable
    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta letherMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            assert letherMeta != null;
            letherMeta.setColor(color);
            itemStack.setItemMeta(letherMeta);
        } catch (ClassCastException ignored) {}
        return this;
    }

    /**
     * add one enchantment to the itemMeta
     * @param enchantment The enchantment to add
     * @param level       The power level of the enchantment
     *                    every possible Integer value is possible
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, Integer level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * add multiple enchantments to the itemMeta
     * @param enchantments the values for the enchantments
     *                     values: Enchantment, Level
     */
    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {

        enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, true));

        return this;
    }

    /**
     * remove a single enchantment
     * @param enchantment the enchantment to remove
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }


    /**
     * remove multiple enchantments
     * @param enchantments the enchantments to remove
     */
    public ItemBuilder removeEnchantments(Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            itemMeta.removeEnchant(enchantment);
        }
        return this;
    }


    /**
     * set the owner of a skull
     * @param owner the owners name of the skull
     * @deprecated because of the usage of a deprecated Object named SkullMeta
     */
    @Deprecated(since = "V 1.0", forRemoval = false)
    public ItemBuilder setSkullOwner(String owner) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        assert skullMeta != null;
        skullMeta.setOwner(owner);
        return this;
    }

    /**
     * set the durability to the max Durability
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder repairItem() {
        itemStack.setDurability(itemStack.getType().getMaxDurability());
        return this;
    }

    /**
     * returns the processed itemStack
     * @return the processed itemStack
     */
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
