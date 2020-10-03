/**
 * Ejercicio.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre_ejercicio:{
      type: 'string',
      required:true,
      columnName:'nombreEjercicio',
      maxLength:50
    },

    url_ejercicio:{
      type: 'string',
      defaultsTo: 'None',
      columnName:'urlEjercicio',
      maxLength:5000
    }, 
    url_Imagen:{
      type: 'string',
      defaultsTo: '',
      columnName:'urlImagen',
      maxLength:400
    },

    tiempo:{
      type: 'string',
      defaultsTo:"100 segundos",
    },

    rutina:{
      model: 'rutina'
    }, 

  },

};

