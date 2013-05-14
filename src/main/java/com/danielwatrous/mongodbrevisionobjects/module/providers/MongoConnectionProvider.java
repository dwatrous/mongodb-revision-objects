/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.module.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.mongodb.DBAddress;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author watrous
 */
public class MongoConnectionProvider implements Provider<Mongo> {
    private List<String> mongoNodes;
    List<ServerAddress> mongoNodesDBAddresses;
    MongoOptions options;

    @Inject
    public MongoConnectionProvider(@Named("mongoNodes") String mongoNodesRaw) {
        // convert mongoNodes into a List
        mongoNodes = Arrays.asList(mongoNodesRaw.split("\\s*,\\s*"));
        try {
            // create addresses for each replicaset node
            List<String> mongoNodes = getMongoNodes();

            mongoNodesDBAddresses = new ArrayList<ServerAddress>(mongoNodes.size());
            for (String mongoNode : mongoNodes) {
                mongoNodesDBAddresses.add(new DBAddress(mongoNode));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error initializing mongo db", e);
        }
        options = new MongoOptions();
        options.setReadPreference(ReadPreference.nearest()); // http://stackoverflow.com/questions/6520439/how-to-configure-mongodb-java-driver-mongooptions-for-production-use
    }

    @Override
    public Mongo get() {
        final Mongo mongo = new Mongo(mongoNodesDBAddresses, options);
        return mongo;
    }

    protected List<String> getMongoNodes() {
        return mongoNodes;
    }
}
