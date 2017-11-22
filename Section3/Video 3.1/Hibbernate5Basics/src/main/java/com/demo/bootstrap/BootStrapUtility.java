package com.demo.bootstrap;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.demo.model.User;

/**
 * @author ankidaemon
 *
 */
public class BootStrapUtility {
	
	public static SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		StandardServiceRegistryBuilder standardBuilder = new StandardServiceRegistryBuilder();

        Map<String, String> cfg = new HashMap<>();
        cfg.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        cfg.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/DemoDB");
        cfg.put(Environment.USER, "root");
        cfg.put(Environment.PASS, "root");
        cfg.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        cfg.put(Environment.SHOW_SQL, "true");
        cfg.put(Environment.HBM2DDL_AUTO, "create");
        
        standardBuilder.applySettings(cfg);

        ServiceRegistry standardRegistry = standardBuilder.build();

        MetadataSources sources = new MetadataSources(standardRegistry);
        
        sources.addAnnotatedClass(User.class);
        
        //Using metadataBuilder apply naming strategy, schema etc 
        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
                
        Metadata metadata = metadataBuilder.build();

        return metadata.getSessionFactoryBuilder().build();
	}
	
	public static void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
