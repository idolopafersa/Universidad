/*
Sustituir este comentario por una explicación de la formula o procedimiento empleado para determinar el valor de una
celda (MapNode).
*/

package net.agsh.towerdefense.strats;

import net.agsh.towerdefense.*;

import java.util.ArrayList;

public class TowerPlacer {

    public static float getNodeValue(MapNode node, Map map) {
        return 1f;
    }

    public static boolean collide(Point2D entity1Position, float entity1Radius,
                                  Point2D entity2Position, float entity2Radius) {
        return false;
    }

    public static ArrayList<Tower> placeTowers(ArrayList<Tower> towers, Map map) {
        // This is just a (bad) example to show you how to use the entities in the game.
        // Replace ALL of this with your own code.

        // Get map size.
        float mapWidth = map.getSize().x;
        float mapHeight = map.getSize().y;

        // Get parameters from the game.
        float maxEnemyRadius = Game.getInstance().getParam(Config.Parameter.ENEMY_RADIUS_MAX);
        float maxRange = Game.getInstance().getParam(Config.Parameter.TOWER_RANGE_MAX);

        // Get nodes the enemies can walk on.
        ArrayList<MapNode> walkableNodes = map.getWalkableNodes();

        // Get obstacles.
        ArrayList<Obstacle> obstacles = map.getObstacles();

        // Get the grid of map nodes.
        MapNode[][] grid = map.getNodes();

        // Get the distance between two nodes.
        MapNode n1 = grid[0][0];
        MapNode n2 = grid[0][1];
        float distance = n1.getPosition().distance(n2.getPosition());

        // Each node has up to Config.Parameter.MAP_NODE_VALUES values. Default is 5.
        // You can *optionally* use these values to store information about the nodes.
        // Set the second value of node n1.
        n1.setValue(2, getNodeValue(n1, map));

        // Get the second value of node n1.
        float value = n1.getValue(2);

        Random random = new Random();
        // Loop through the towers.
        ArrayList<Tower> placedTowers = new ArrayList<>();
        for (Tower tower : towers) {
            // Place the tower on a random node.
            MapNode node = grid[random.nextInt(grid.length)][random.nextInt(grid[0].length)];
            tower.setPosition(node.getPosition());
            placedTowers.add(tower);
        }

        return placedTowers;
    }
}
