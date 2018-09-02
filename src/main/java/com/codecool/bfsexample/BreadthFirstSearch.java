package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BreadthFirstSearch {

    int distance;
    Map<UserNode, Integer> userDistance;

    public void breadthFirstSearch(UserNode starting, UserNode searched) throws IllegalStateException{

        LinkedList<UserNode> toCheck = new LinkedList<>();
        LinkedList<UserNode> checked = new LinkedList<>();

        userDistance = new HashMap<>();

        toCheck.add(starting);
        userDistance.put(starting, 0);

        while(!toCheck.isEmpty()) {
            UserNode currentNode = toCheck.remove();
            this.distance = userDistance.get(currentNode);
            if(currentNode.equals(searched)) {
                return;
            }

            checked.add(currentNode);

            for(UserNode friend: currentNode.getFriends()) {
                userDistance.put(friend, this.distance + 1);
                if(!checked.contains(friend)) {
                    toCheck.add(friend);
                }
            }

        }
        throw new IllegalArgumentException();
    }

    public Set<UserNode> friendsOfFriends(UserNode friendsOfNode, int distance) {
        Set<UserNode> friendsSet = new HashSet<>();

        LinkedList<UserNode> toCheck = new LinkedList<>();
        LinkedList<UserNode> checked = new LinkedList<>();

        userDistance = new HashMap<>();

        toCheck.add(friendsOfNode);
        userDistance.put(friendsOfNode, 0);

        while(!toCheck.isEmpty()) {
            UserNode currentNode = toCheck.remove();
            int depth = userDistance.get(currentNode);

            checked.add(currentNode);

            if(depth <= distance) {
                if(!currentNode.equals(friendsOfNode)) {
                    friendsSet.add(currentNode);
                }
                for(UserNode friendNode: currentNode.getFriends()) {
                    if(!checked.contains(friendNode)) {
                        userDistance.put(friendNode, depth + 1);
                        toCheck.add(friendNode);
                    }
                }
            }
        }

        return friendsSet;
    }

    public List<String> findShortest(String starting, String ending, List<UserNode> users) {
        List<String> path = new ArrayList<>();
        LinkedList<UserNode> toCheck = new LinkedList<>();
        LinkedList<UserNode> checked = new LinkedList<>();
        Map<String, UserNode> previous = new HashMap<>();

        UserNode startingNode = new UserNode();
        for(UserNode node : users) {
            if(node.getFullName().equals(starting)) {
                startingNode = node;
            }
        }

        toCheck.add(startingNode);
        UserNode currentNode = new UserNode();

        while(!toCheck.isEmpty()) {
            currentNode = toCheck.remove();
            checked.add(currentNode);

            if(currentNode.getFullName().equals(ending)) {
                break;
            }
            for(UserNode friend: currentNode.getFriends()) {
                if(!checked.contains(friend)) {
                    toCheck.add(friend);
                    previous.put(friend.getFullName(), currentNode);
                }
            }
        }
        for(UserNode endingNode = currentNode; endingNode != null; endingNode = previous.get(endingNode.getFullName())) {
            path.add(endingNode.getFullName());
        }
        return path;
    }

    public int getDistance() {
        return distance;
    }

    public Map<UserNode, Integer> getUserDistance() {
        return userDistance;
    }
}
