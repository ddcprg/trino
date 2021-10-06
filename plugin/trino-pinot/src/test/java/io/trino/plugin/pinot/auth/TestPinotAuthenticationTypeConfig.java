/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.pinot.auth;

import com.google.common.collect.ImmutableMap;
import io.airlift.configuration.testing.ConfigAssertions;
import org.testng.annotations.Test;

import java.util.Map;

import static io.trino.plugin.pinot.auth.PinotAuthenticationType.BASIC_INLINE;
import static io.trino.plugin.pinot.auth.PinotAuthenticationType.NONE;

public class TestPinotAuthenticationTypeConfig
{
    @Test
    public void testDefaults()
    {
        ConfigAssertions.assertRecordedDefaults(
                ConfigAssertions.recordDefaults(PinotAuthenticationTypeConfig.class)
                        .setControllerAuthenticationType(NONE)
                        .setBrokerAuthenticationType(NONE));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = new ImmutableMap.Builder<String, String>()
                .put("pinot.authentication.controller.type", "basic_inline")
                .put("pinot.authentication.broker.type", "basic_inline")
                .build();

        PinotAuthenticationTypeConfig expected = new PinotAuthenticationTypeConfig()
                .setControllerAuthenticationType(BASIC_INLINE)
                .setBrokerAuthenticationType(BASIC_INLINE);

        ConfigAssertions.assertFullMapping(properties, expected);
    }
}
