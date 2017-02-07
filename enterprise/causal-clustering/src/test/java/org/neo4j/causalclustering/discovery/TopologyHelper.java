/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.causalclustering.discovery;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.helpers.AdvertisedSocketAddress;

import static java.util.Collections.singletonList;
import static org.neo4j.causalclustering.discovery.ClientConnectorAddresses.Scheme.bolt;

public class TopologyHelper
{
    public static Set<ReadReplicaAddresses> addressesForReadReplicas( int... ids )
    {
        return Arrays.stream( ids ).mapToObj( TopologyHelper::addressesForReadReplica ).collect( Collectors.toSet() );
    }

    public static CoreAddresses adressesForCore( int id )
    {
        AdvertisedSocketAddress advertisedSocketAddress = new AdvertisedSocketAddress( "localhost", (3000 + id) );
        return new CoreAddresses( advertisedSocketAddress, advertisedSocketAddress,
                new ClientConnectorAddresses( singletonList( new ClientConnectorAddresses.ConnectorUri( bolt, advertisedSocketAddress ) ) ) );
    }

    public static ReadReplicaAddresses addressesForReadReplica( int id )
    {
        AdvertisedSocketAddress advertisedSocketAddress = new AdvertisedSocketAddress( "localhost", (3000 + id) );
        return new ReadReplicaAddresses(
                new ClientConnectorAddresses( singletonList( new ClientConnectorAddresses.ConnectorUri( bolt, advertisedSocketAddress ) ) ) );
    }
}