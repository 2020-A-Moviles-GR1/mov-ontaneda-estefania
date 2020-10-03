/**
 * RecetaUsuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_recetaUsuario:{
      type: 'number',
      autoIncrement:true,
      columnName:'idRecetaUsuario'
    },
    fechaRealizacion:{
      type: 'string',
      defaultsTo: '2020-08-26',
      columnName: 'FechaRealizacion'
    },

    receta:{
      model: 'receta'
    },

    usuario:{
      model: 'usuario'
    }
  },

};

