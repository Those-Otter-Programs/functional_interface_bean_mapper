# Functional Interface Bean Mapper

[![Buid Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

[Project repository](https://github.com/Those-Otter-Programs/functional_interface_bean_mapper)

## Description:
Sampling the use of functional interfaces to create a bean mapper.

## Features:

- The project implements a mapper called MemberMapper which is a Functional Interface with two lambda expressions, one to map MemberEntity to MemberResponse and the other way for the second.

## Controllers

- [MemberController](#membercontroller)

---

## H2:

```bash
http://localhost:8080/h2
```
## MemberController

This controller implements 2 actions:

```bash
 [GET] /member{id}
 [GET] /members
```

***ROUTES:***

**/member/{id}**

```bash
# JSON response:
curl -s -L -X GET 'http://localhost:8080/member/1' | jq

```

**/members** - paginated route

```bash
# JSON response:
curl -s -L -X GET 'http://localhost:8080/members?page=0&size=8&sort=asc' | jq
curl -s -L -X GET 'http://localhost:8080/members?page=0&size=8' | jq
curl -s -L -X GET 'http://localhost:8080/members?page=0' | jq
curl -s -L -X GET 'http://localhost:8080/members' | jq

```

---

## Author: James Mallon
- [github](https://github.com/jamesmallon)
- [linkedin](https://www.linkedin.com/in/roccojamesmallon/)

## License
[Apache 2.0](LICENSE)

---

![Have a good one](src/main/resources/imgs/chill_otter.jpg)

---
> "It's your reaction to adversity, not adversity itself that determines how your life's story will develop." – Dieter F. Uchtdorf.