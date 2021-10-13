(ns clojure-database-postgres.core
  (:require [environ.core :refer [env]])
  (:require [clojure.java.jdbc :as jdbc])
  (:gen-class))

(defn user-info [uri]
  (if (nil? (.getUserInfo uri))
    nil (clojure.string/split (.getUserInfo uri) #":")))


(def db-uri (java.net.URI. (env :database-url)))
(def db-host (.getHost db-uri))
(def db-port (if (> (.getPort db-uri) 0) (.getPort db-uri) 5432))
(def db-name (.substring (.getPath db-uri) 1))
(def db-username (get (user-info db-uri) 0))
(def db-password (get (user-info db-uri) 0))

(def db-spec {:classname "org.postgresql.Driver" 
              :subprotocol "postgresql"
              :subname (str "//" db-host ":" db-port "/" db-name)
              :user db-username
              :password db-password})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (jdbc/execute! db-spec ["CREATE TABLE IF NOT EXISTS person (id SERIAL PRIMARY KEY, first_name VARCHAR NOT NULL, last_name VARCHAR NOT NULL);"])
  (jdbc/insert-multi! db-spec :person
                    nil ; column names not supplied
                    [[1 "John" "Doe"]
                     [2 "Jane" "Doe"]])
  (println (jdbc/query db-spec ["select * from person"] {:result-set-fn first})))
