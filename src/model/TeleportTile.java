package model;

public class TeleportTile extends Tile{

    public TeleportTile(int xPosition, int yPosition, char symbol) {
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    @Override
    public void onPlayerEnter(Player player, Maps map){
        if (!player.getInventory().hasBoots("teleportboots")) {
        // Send back to start
        player.setPosition(map.getPlayerStartX(), map.getPlayerStartY());
    }

    }


}