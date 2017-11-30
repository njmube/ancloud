package org.ancloud.config;

import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.domain.resource.Resource;
import org.ancloud.repository.jpa.ResourceRepository;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomEnvironmentRepository implements EnvironmentRepository {

  
  @Inject
  ResourceRepository resourceRepository;
  
  @Override
  public Environment findOne(String application, String profile, String label) {
    Environment environment = new Environment(application, profile);

    final Map<String, String> properties = resourceRepository.findAllByCategory(SystemConstant.APPLICATION_PROPERTIES+"_"+profile)
                                                            .stream()
                                                            .collect(Collectors.toMap(Resource::getResourceKey, Resource::getValue));
    environment.add(new PropertySource("mapPropertySource", properties));
    return environment;
  }

}
