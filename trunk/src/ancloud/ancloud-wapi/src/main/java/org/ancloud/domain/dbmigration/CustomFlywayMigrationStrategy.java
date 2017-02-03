package org.ancloud.domain.dbmigration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

public class CustomFlywayMigrationStrategy implements FlywayMigrationStrategy {
	Logger logger = LoggerFactory.getLogger(CustomFlywayMigrationStrategy.class);
	@Override
	public void migrate(Flyway flyway) {
		try{
			flyway.migrate();
		} catch (FlywayException ex){
			// TODO implement error check on failed migration
//			flyway.repair();
			throw ex;
		}
	}

}
