# Tour agency - web project

## The short structure of project
- src
  - ua.nure.miroshnichenko.myorm - The implementation of my own ORM and connections pool.
    - ua.nure.miroshnichenko.myorm.annotation - All annotations of ORM. 
    - ua.nure.miroshnichenko.myorm.core - The implmentation of main classes.
    - ua.nure.miroshnichenko.myorm.pool - Interfaces represent the connection pool.
    - ua.nure.miroshnichenko.myorm.mapping.Mapper - Util class for mapping SQL queries and java objects.
    - ua.nure.miroshnichenko.myorm.transaction - Interfaces represent the high-level of ORM.
  - ua.nure.miroshnichenko.touragency
    - ua.nure.miroshnichenko.touragency.dao - The layer of DAO classes.
    - ua.nure.miroshnichenko.touragency.service - The layer of services.
    - ua.nure.miroshnichenko.touragency.web
      - ua.nure.miroshnichenko.touragency.web.Controller - The front controller.
      - ua.nure.miroshnichenko.touragency.web.action - The layer of controllers.
      - ua.nure.miroshnichenko.touragency.web.filter - Filters.
      - ua.nure.miroshnichenko.touragency.listener - Listeners.
- test - Test classes
- WebContent - The main JSP files.
  - css - Styles.
  - js Javascript files.
  - photo - The folder with saved photos of hotels.
  - WEB-INF - Include tags and other JSP files.
