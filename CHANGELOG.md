# Semantic Versioning Changelog

# [1.7.0](https://github.com/JayDamon/budget-service/compare/v1.6.1...v1.7.0) (2022-01-17)


### Features

* **spring:** update spring boot and spring cloud version ([ca7810e](https://github.com/JayDamon/budget-service/commit/ca7810e86d333d0c11ed6b5a12d4ec0e7098ade5))

## [1.6.1](https://github.com/JayDamon/budget-service/compare/v1.6.0...v1.6.1) (2022-01-16)


### Bug Fixes

* **build:** Update docker java version ([b2bc6dc](https://github.com/JayDamon/budget-service/commit/b2bc6dcab3d12abd93dbab8aff51935f49912f33))

# [1.6.0](https://github.com/JayDamon/rin-budget-service/compare/v1.5.0...v1.6.0) (2021-11-22)


### Features

* **ci:** Update ci with new build script ([14e8d3c](https://github.com/JayDamon/rin-budget-service/commit/14e8d3c189bea1e9a4e81131ac0728f22397e3d4))

# [1.5.0](https://github.com/JayDamon/rin-budget-service/compare/v1.4.0...v1.5.0) (2021-11-13)


### Features

* **jdk:** Java 17 upgrade ([d857910](https://github.com/JayDamon/rin-budget-service/commit/d857910bc48b4cfa64567061b11bf623ffff90be))

# [1.4.0](https://github.com/JayDamon/rin-budget-service/compare/v1.3.0...v1.4.0) (2021-08-26)


### Features

* **rabbit:** Add rabbit mq messaging queue for async communication ([d0e602e](https://github.com/JayDamon/rin-budget-service/commit/d0e602efa16bf828d58f4106823cfc5c7cb98698))

# [1.3.0](https://github.com/JayDamon/rin-budget-service/compare/v1.2.0...v1.3.0) (2021-08-19)


### Features

* **h2:** Add local config to allow for the h2 console ([0bacdb6](https://github.com/JayDamon/rin-budget-service/commit/0bacdb6b29d2dca59a9641036c0fe159781a8f9e))

# [1.2.0](https://github.com/JayDamon/rin-budget-service/compare/v1.1.3...v1.2.0) (2021-08-19)


### Features

* **Budget:** require category id when creating new budget ([ec6e22f](https://github.com/JayDamon/rin-budget-service/commit/ec6e22f73a2f0f06e5a1a35b386f6fef428f9459))

## [1.1.3](https://github.com/JayDamon/rin-budget-service/compare/v1.1.2...v1.1.3) (2021-08-13)


### Bug Fixes

* **kubernetes:** Use fabric8 client to avoid log error" ([4d52ef3](https://github.com/JayDamon/rin-budget-service/commit/4d52ef39118e24a8b8f5bdce8e5b1b95b5345f09))

## [1.1.2](https://github.com/JayDamon/rin-budget-service/compare/v1.1.1...v1.1.2) (2021-08-11)


### Bug Fixes

* **budget:** Add missing group by ([0ca6577](https://github.com/JayDamon/rin-budget-service/commit/0ca657730597a56826540e3ca7bda129e602084e))

## [1.1.1](https://github.com/JayDamon/rin-budget-service/compare/v1.1.0...v1.1.1) (2021-08-11)


### Bug Fixes

* **budget:** Use budget type over budget id ([be29197](https://github.com/JayDamon/rin-budget-service/commit/be29197ef44267e254c574b46d46e4b902cbd011))

# [1.1.0](https://github.com/JayDamon/rin-budget-service/compare/v1.0.0...v1.1.0) (2021-08-08)


### Features

* **security:** Add discriminator based multi tenancy ([c363be3](https://github.com/JayDamon/rin-budget-service/commit/c363be307c1fe1df65e55049a08fa1ecad2dabde))

# [1.0.0](https://github.com/JayDamon/rin-budget-service/compare/v0.5.0...v1.0.0) (2021-08-06)


### Bug Fixes

* **ci:** Change docker file to point to maven output directory ([8974532](https://github.com/JayDamon/rin-budget-service/commit/89745322cd4eb9a63380898e0f4e1ee39c6782ab))
* **ci:** Fix main branch only exclusion ([53a0264](https://github.com/JayDamon/rin-budget-service/commit/53a0264fc20124d183a424f9f575f73eca52ee3f))


### Features

* **build:** Convert to maven ([1817366](https://github.com/JayDamon/rin-budget-service/commit/18173661883de72bb986df5fc6c18996c947a11e))
* **ci:** limit to only main branch for circle CI ([b9c3084](https://github.com/JayDamon/rin-budget-service/commit/b9c3084f24f09566e82acd8e843ee45e488f999f))


### BREAKING CHANGES

* **build:** now usint maven instead of gradle to keep consistency for all apps

# [0.5.0](https://github.com/JayDamon/rin-budget-service/compare/v0.4.1...v0.5.0) (2021-08-05)


### Features

* **security:** Integrate local with oauth2/keycloak ([b84bd89](https://github.com/JayDamon/rin-budget-service/commit/b84bd898ba2d2b1de242c0f1346deb6bc55726a1))

## [0.4.1](https://github.com/JayDamon/rin-budget-service/compare/v0.4.0...v0.4.1) (2021-07-27)


### Bug Fixes

* **db:** Fix flyway data load ([c062e95](https://github.com/JayDamon/rin-budget-service/commit/c062e9507eb234eaa4de5a9ff633de66e93015db))

# [0.4.0](https://github.com/JayDamon/rin-budget-service/compare/v0.3.4...v0.4.0) (2021-07-27)


### Features

* **client:** Add local docker config ([d150abd](https://github.com/JayDamon/rin-budget-service/commit/d150abde80f73074d8a6452aec9c6d107e704018))

## [0.3.4](https://github.com/JayDamon/rin-budget-service/compare/v0.3.3...v0.3.4) (2021-07-26)


### Bug Fixes

* **client:** Remove Ports ([e20c555](https://github.com/JayDamon/rin-budget-service/commit/e20c5551badb72535cb58a29d45a45e4c2964f86))

## [0.3.3](https://github.com/JayDamon/rin-budget-service/compare/v0.3.2...v0.3.3) (2021-07-25)


### Bug Fixes

* **client:** Set port for http requests ([7d1db58](https://github.com/JayDamon/rin-budget-service/commit/7d1db58aa121051355968e86ee1b6d8f6a475779))

## [0.3.2](https://github.com/JayDamon/rin-budget-service/compare/v0.3.1...v0.3.2) (2021-07-25)


### Bug Fixes

* **flyway:** Properly setup flyway ([9bd8ba1](https://github.com/JayDamon/rin-budget-service/commit/9bd8ba1e2b9cda2fc14583d276ea0f7b7e659e4a))

## [0.3.1](https://github.com/JayDamon/rin-budget-service/compare/v0.3.0...v0.3.1) (2021-07-25)


### Bug Fixes

* **discovery:** Use generic discovery client ([38830da](https://github.com/JayDamon/rin-budget-service/commit/38830daa1584acb8192c3d2fd28c091a1b003c7d))

# [0.3.0](https://github.com/JayDamon/rin-budget-service/compare/v0.2.0...v0.3.0) (2021-07-25)


### Bug Fixes

* **tests:** Use updated api paths ([8de4ed1](https://github.com/JayDamon/rin-budget-service/commit/8de4ed1111490d12538d6ae8faeb16a14be6810b))


### Features

* **api:** Update api to work with k8s gateway ([0421ad4](https://github.com/JayDamon/rin-budget-service/commit/0421ad4b5112ec782b32885d516489ab3cce1eaa))

# [0.1.0](https://github.com/JayDamon/rin-budget-service/compare/v0.0.1...v0.1.0) (2021-07-21)


### Bug Fixes

* **ci:** take ownership of gradle file ([8c6c32a](https://github.com/JayDamon/rin-budget-service/commit/8c6c32aa2ad94a7b99463145a84ccd23f01cf0e2))
* **ci:** take ownership of gradle file ([5ba1723](https://github.com/JayDamon/rin-budget-service/commit/5ba1723e5f18f279f3dfda80cbcfb4319c5449ed))
* **gradle:** remove plain jar from buid output ([5d742be](https://github.com/JayDamon/rin-budget-service/commit/5d742be108e40f8522039d267acbaf879db61649))
* **gradle:** take ownership of gradlew and remove commented code ([7edd780](https://github.com/JayDamon/rin-budget-service/commit/7edd780b11cb3b4e29b5d1856e90792371c9ce6e))
* **versioning:** change repo name in .releaserc ([21933a5](https://github.com/JayDamon/rin-budget-service/commit/21933a56067aec9debcf76c713698b161f96e2c9))


### Features

* **config:** Add circleci, semantic-release, kubernetes ([78995bf](https://github.com/JayDamon/rin-budget-service/commit/78995bff70e20e4e4ea789eca3c2dec309a8d466))
