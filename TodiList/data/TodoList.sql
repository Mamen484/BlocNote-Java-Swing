/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  23/10/2017 09:38:43                      */
/*==============================================================*/


drop table if exists categories;

drop table if exists taches;

/*==============================================================*/
/* Table : categories                                           */
/*==============================================================*/
create table categories
(
   id_categorie         int not null,
   nomDeCategorie       varchar(254),
   primary key (id_categorie)
);

/*==============================================================*/
/* Table : taches                                               */
/*==============================================================*/
create table taches
(
   id_tache             int not null,
   id_categorie         int not null,
   nomDeTache           varchar(254),
   tacheTerminee        bool,
   primary key (id_tache)
);

alter table taches add constraint FK_categories_taches foreign key (id_categorie)
      references categories (id_categorie) on delete restrict on update restrict;

