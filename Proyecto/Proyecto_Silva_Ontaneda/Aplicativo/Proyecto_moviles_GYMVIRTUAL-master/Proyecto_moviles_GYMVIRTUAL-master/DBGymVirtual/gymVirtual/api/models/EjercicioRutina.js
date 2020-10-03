/**
 * EjercicioRutina.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_ejercicioRutina:{
      type: 'number',
      autoIncrement:true,
      columnName:'idEjerRut'
    },
    repeticiones_EjeRut:{
      type: 'number',
      defaultsTo: 1,
      columnName:'repetEjerRut'
    },
    /*ejercicio1:{
      model: 'ejercicio'
    },
    ejercicio2:{
      model: 'ejercicio'
    },
    ejercicio3:{
      model: 'ejercicio'
    },
    ejercicio4:{
      model: 'ejercicio'
    },*/ 
    /*ejerciciosRutinas:{
      collection: 'ejercicioRutina',
      via:'ejercicio'
    },
    usuarioRutinasEjercicios:{
      collection: 'usuarioRutinaEjercicio',
      via: 'ejercicioRutina'
    }*/
  },

};

