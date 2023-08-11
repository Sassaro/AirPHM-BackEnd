package org.example.dataObjects;

import org.apache.tomcat.jni.User;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Node
public class UserNeo4jDataObject {
   @Id
   Long id;
   String username;
   List<UserNeo4jDataObject> friendList;

    public <R> UserNeo4jDataObject(Long id, String username, List<UserNeo4jDataObject> friendList) {
        this.id = id;
        this.username = username;
        this.friendList = friendList;
    }
}
