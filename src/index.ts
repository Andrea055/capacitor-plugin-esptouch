import { registerPlugin } from '@capacitor/core';

import type { EsptouchCapacitorPluginPlugin } from './definitions';

const EsptouchCapacitorPlugin = registerPlugin<EsptouchCapacitorPluginPlugin>(
  'EsptouchCapacitorPlugin',
  {
    web: () => import('./web').then(m => new m.EsptouchCapacitorPluginWeb()),
  },
);

export * from './definitions';
export { EsptouchCapacitorPlugin };
