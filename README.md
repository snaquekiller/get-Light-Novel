# get-Light-Novel

It's a project for get light novel and transform it into ebook ready to be read.

# Documentation :

- https://www.ibm.com/developerworks/xml/tutorials/x-epubtut/index.html
- https://react-bootstrap.github.io/getting-started/introduction/
- https://v4-alpha.getbootstrap.com/components/forms/
- https://www.baeldung.com/get-user-in-spring-security
- https://dzone.com/articles/secure-spring-rest-with-spring-security-and-oauth2

# Creation of password 
Jshell idea: 
```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
System.out.println(new BCryptPasswordEncoder().encode("test"));
```

# Postman
https://www.getpostman.com/collections/38e2680c05046fc3feb3


# ERROR

Traceback (most recent call last):
  File "site-packages/calibre/gui2/tweak_book/job.py", line 35, in run
  File "site-packages/calibre/gui2/tweak_book/boss.py", line 59, in get_container
  File "site-packages/calibre/ebooks/oeb/polish/container.py", line 1480, in get_container
  File "site-packages/calibre/ebooks/oeb/polish/container.py", line 1138, in __init__
InvalidEpub: No META-INF/container.xml in epub
