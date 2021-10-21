package com.vibhor.redismasterslave.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "redis")
public class Config {

    private Instance master;

    public Instance getMaster() {
        return master;
    }

    public void setMaster(Instance master) {
        this.master = master;
    }

    public List<Instance> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<Instance> slaves) {
        this.slaves = slaves;
    }

    private List<Instance> slaves;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();
        RedisStaticMasterReplicaConfiguration staticMasterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(this.getMaster().getHost(), this.getMaster().getPort());
        this.getSlaves().forEach(slave -> staticMasterReplicaConfiguration.addNode(slave.getHost(),slave.getPort()));
        return new LettuceConnectionFactory(staticMasterReplicaConfiguration, clientConfig);
    }




    public static class Instance {

        private String host;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        private int port;



    }
}
