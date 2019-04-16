/*
*  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.extension.siddhi.execution.geo.internal.impl;

import io.siddhi.core.util.config.ConfigReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.extension.siddhi.execution.geo.api.GeoLocationResolver;
import org.wso2.extension.siddhi.execution.geo.api.Location;
import org.wso2.extension.siddhi.execution.geo.internal.exception.GeoLocationResolverException;

/**
 * The default implementation of the GeoLocationResolver interface. This is implemented based on RDBMS.
 */
public class DefaultDBBasedGeoLocationResolver implements GeoLocationResolver {
    private static final Log log = LogFactory.getLog(DefaultDBBasedGeoLocationResolver.class);

    @Override
    public void init(ConfigReader configReader) throws GeoLocationResolverException {
        RDBMSGeoLocationResolver.getInstance().init(configReader);
    }

    @Override
    public Location getGeoLocationInfo(String ip) {
        Location location = null;
        try {
            location = RDBMSGeoLocationResolver.getInstance().getLocation(ip);
        } catch (GeoLocationResolverException e) {
            log.warn("Cannot retrieve the location against the ip '" + ip + "'");
        }
        return location != null ? location : new Location("", "", ip);
    }

}
