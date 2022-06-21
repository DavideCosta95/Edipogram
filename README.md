# EDIPOGRAM  

## Descrizione  
Modifica del [progetto proposto](https://github.com/aswroma3/asw/tree/master/projects/asw-edipogram) per il [corso di Architetture dei Sistemi Software](http://cabibbo.inf.uniroma3.it/asw/index.html) A.A. 2021/22 rispetto ai [requisiti forniti](/assets/docs/requisiti.pdf).

## Caratteristiche  
- Le modifiche apportate sono state versionate applicando le pratiche di Gitflow;  
- I messaggi di commit seguono la specifica dei [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/);  
- Il semantic versioning applicato è riferito al funzionamento interno del sistema e non alle API offerte, che rimangono invariate sotto ogni aspetto rispetto alla versione di base del progetto;  
- Le modifiche all'implementazione del servizio `enigmi-seguiti` rispetto all'aggregazione dei dati degli altri due servizi, è realizzata tramite una vista materializzata, aggiornata dal DBMS ad ogni modifica alle tabelle locali relative ad `enigmi` e `connessioni` per mezzo di un trigger, limitando il carico e le responsabilità del codice applicativo;  
- È stato mantenuto Consul per il service discovery anche nell'ambito del deploy su Kubernetes per le seguenti motivazioni:  
  - Rende possibili entrambi i tipi di deploy forniti senza alcuna modifica né delle configurazioni né della code base;
  - Non accoppia il service discovery all'infrastruttura e modalità di deploy, rendendo possibile nel caso di Kubernetes di comunicare con servizi gestiti da Consul anche esternamente al cluster.  

## Tipologie di deploy  
Sono disponibili due modalità di deploy del sistema:
- run dei servizi contenitorizzati tramite `docker compose`
- run dei servizi tramite l'orchestratore di container `Kubernetes`

In entrambi i casi sono disponibili degli script per il popolamento e il testing dell'applicazione nella cartella [shell-scripts](/shell-scripts), già forniti nel progetto base originale e non modificati in questa sede, non avendo le API esposte subito variazioni.

## Deploy tramite docker compose  
- Per avviare l'applicazione, eseguire lo script [run-edipogram.sh](/deploy/docker-compose/run-edipogram.sh).  
- Per arrestarla, eseguire [stop-edipogram.sh](/deploy/docker-compose/stop-edipogram.sh).  

## Deploy tramite Kubernetes  
Lo script completo di deploy per Kubernetes è presente nella cartella [manifests](/deploy/kubernetes/manifests).  
Suddetta cartella viene montata come volume tramite docker compose nell'ambito della creazione di un container di `k3s` per fornire un ambiente di deploy riproducibile durante lo sviluppo e il testing.  

- Per avviare l'applicazione, eseguire lo script [run-edipogram.sh](/deploy/kubernetes/run-edipogram.sh).  
- Per arrestarla, eseguire [stop-edipogram.sh](/deploy/kubernetes/stop-edipogram.sh).  

Per gestire il cluster una volta avviato, utilizzare il comando `kubectl` con il parametro `--kubeconfig kubeconfig.yaml` che fa riferimento al file di configurazione nella cartella `deploy/kubernetes`, autogenerato durante la creazione del cluster.  