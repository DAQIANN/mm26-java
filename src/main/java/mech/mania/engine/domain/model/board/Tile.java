package mech.mania.engine.domain.model.board;

import mech.mania.engine.domain.model.BoardProtos;
import mech.mania.engine.domain.model.ItemProtos;
import mech.mania.engine.domain.model.items.*;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private List<Item> items;
    public enum TileType{
        VOID, BLANK, IMPASSIBLE, PORTAL
    }
    private TileType type;

    public Tile() {
        items = new ArrayList<>();
        type = TileType.BLANK;
    }

    /**
     * Creates a Tile based on a Protocol Buffer.
     * @param tileProto the protocol buffer being copied
     */
    public Tile(BoardProtos.Tile tileProto) {
        items = new ArrayList<>(tileProto.getItemsCount());
        for (int i = 0; i < tileProto.getItemsCount(); i++) {
            ItemProtos.Item protoItem = tileProto.getItems(i);
            switch (protoItem.getItemCase()) {
                case CLOTHES:
                    items.add(i, new Clothes(protoItem.getClothes()));
                    break;
                case HAT:
                    items.add(i, new Hat(protoItem.getHat()));
                    break;
                case SHOES:
                    items.add(i, new Shoes(protoItem.getShoes()));
                    break;
                case WEAPON:
                    items.add(i, new Weapon(protoItem.getWeapon()));
                    break;
                case CONSUMABLE:
                    items.add(i, new Consumable(protoItem.getMaxStack(), protoItem.getConsumable()));
            }
        }

        switch (tileProto.getTileType()) {
            case VOID:
                type = TileType.VOID;
                break;
            case BLANK:
                type = TileType.BLANK;
                break;
            case IMPASSIBLE:
                type = TileType.IMPASSIBLE;
                break;
            case PORTAL:
                type = TileType.PORTAL;
                break;
        }
    }

    /**
     * Getter for the list of Items on a Tile instance.
     * @return the list of all Items on the Tile
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Getter for the type of a Tile instance.
     * @return the type of the Tile
     */
    public TileType getType() {
        return type;
    }
}
