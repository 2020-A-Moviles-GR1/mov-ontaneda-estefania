/**
 * UsuarioRutinaEjercicio.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_usuarioRutEjer:{
      type: 'number',
      autoIncrement:true,
      columnName:'idUsuRutEjer'
    },

    numRepEjecutadas:{
      type: 'number',
      defaultsTo: 0,
      columnName:'repetEjerRutRealizadas'
    },

    usuarioRutina:{
      model: 'usuarioRutina'
    },

    ejercicioRutina:{
      model: 'ejercicioRutina'
    }
  
  },

};

