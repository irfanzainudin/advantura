package advantura;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        
        switch (entity.direction)
        {
            case "move_up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "move_down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "move_right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "move_left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "idle_up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "idle_down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "idle_right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "idle_left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
        
            default:
                break;
        }
    }
}
