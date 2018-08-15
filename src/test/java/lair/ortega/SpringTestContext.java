package lair.ortega;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"lair.ortega.configuration", "lair.ortega.dao", "lair.ortega.logic", "lair.ortega.model.db"})
public class SpringTestContext{
	
}