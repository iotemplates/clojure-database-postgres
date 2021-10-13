(defproject clojure-database-postgres "0.1.0-SNAPSHOT"
  :description "A basic template for clojure projects."
  :url "http://github.com/iotemplates/clojure-database-postgres"
  :license {:name "Apache License 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"
            :year 2020
            :key "apache-2.0"}
  :dependencies [
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/java.jdbc "0.7.5"]
                 [org.postgresql/postgresql "42.2.24"]
                 [environ "0.5.0"]]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot clojure-database-postgres.core
  :target-path "target/%s"
  :profiles {:dev {:env { :database-url "postgresql://postgres:postgres@localhost/people" }}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
