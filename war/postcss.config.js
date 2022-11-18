/* eslint-env node */
module.exports = (ctx) => ({
  parser:  ctx.env !== 'SIROCCO' ? "postcss-less" : false,
  plugins: 
  {
    autoprefixer:  ctx.env !== 'SIROCCO' ? require('autoprefixer') : false,
    tailwindcss: ctx.env === 'SIROCCO' ? { config: './tailwindcss.config.js' } : false,
    }
  ,
});
