package com.example.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.example.test.model.Nilai;
import com.example.test.model.Siswa;

public class HibernateUtil {
	   private static StandardServiceRegistry registry;
	   private static SessionFactory sessionFactory;

	   public static SessionFactory getSessionFactory() {
	      if (sessionFactory == null) {
	         try {
	            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

	            //Configuration properties
	            Map<String, Object> settings = new HashMap<>();
	            settings.put(Environment.DRIVER, "org.h2.Driver");
	            settings.put(Environment.URL, "jdbc:h2:~/test");
	            settings.put(Environment.USER, "sa");
	            settings.put(Environment.PASS, "");
	            settings.put(Environment.HBM2DDL_AUTO, "validate");
	            settings.put(Environment.SHOW_SQL, true);
	            
	            registryBuilder.applySettings(settings);
	            registry = registryBuilder.build();
	            
	            MetadataSources sources = new MetadataSources(registry);
	            sources.addAnnotatedClass(Siswa.class);
	            sources.addAnnotatedClass(Nilai.class);
	            Metadata metadata = sources.getMetadataBuilder().build();
	            
	            sessionFactory = metadata.getSessionFactoryBuilder().build();
	         } catch (Exception e) {
	            if (registry != null) {
	               StandardServiceRegistryBuilder.destroy(registry);
	            }
	            e.printStackTrace();
	         }
	      }
	      return sessionFactory;
	   }

	   public static void shutdown() {
	      if (registry != null) {
	         StandardServiceRegistryBuilder.destroy(registry);
	      }
	   }
	}
