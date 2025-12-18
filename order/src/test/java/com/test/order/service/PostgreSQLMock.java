package com.test.order.service;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLMock implements BeforeAllCallback, AfterAllCallback {

	private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13")
			.withExposedPorts(PostgreSQLContainer.POSTGRESQL_PORT);

	public static PostgreSQLContainer<?> getInstance() {
		return container;
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		container.stop();
	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		container.start();
		setProperties();
	}

	public static void setProperties() {
		var container = PostgreSQLMock.getInstance();
		System.setProperty("spring.datasource.url", container.getJdbcUrl());
		System.setProperty("spring.datasource.username", container.getUsername());
		System.setProperty("spring.datasource.password", container.getPassword());

	}

	public static void runScript(String sqlScriptPath) {
		try (Connection connection = getConnection()) {
			URL inputStream = PostgreSQLMock.class.getClassLoader().getResource(sqlScriptPath);
			if (inputStream == null) {
				throw new IllegalStateException("Could not find the SQL script: " + sqlScriptPath);
			}
			ScriptUtils.executeSqlScript(connection, new UrlResource(inputStream));
		} catch (NoDriverFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws NoDriverFoundException, SQLException {
		return container.createConnection("");
	}

}
