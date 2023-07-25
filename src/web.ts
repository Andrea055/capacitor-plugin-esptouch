import { WebPlugin } from '@capacitor/core';

import type { EsptouchCapacitorPluginPlugin, EsptouchV2Request, EsptouchV2Response } from './definitions';

export class EsptouchCapacitorPluginWeb
  extends WebPlugin
  implements EsptouchCapacitorPluginPlugin
{
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  EsptouchV2(_options: EsptouchV2Request): Promise<EsptouchV2Response> {
    return Promise.resolve({
      response: "", 
      error: "Not implemented in web"
    });
  }
}
